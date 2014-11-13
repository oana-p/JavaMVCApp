package ro.z2h;

import org.codehaus.jackson.map.ObjectMapper;
import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.controller.DepartmentController;
import ro.z2h.controller.EmployeeController;
import ro.z2h.fmk.AnnotationScanUtils;
import ro.z2h.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyDispatcherServlet extends HttpServlet{

    HashMap<String, MethodAttributes> hashMapReqs;
    @Override
    public void init() throws ServletException {

        hashMapReqs = new HashMap();
        try {

            Iterable<Class> classes = AnnotationScanUtils.getClasses("ro.z2h.controller");

            /* cu shortcut "iter" genereaza for pentru iteratorul de mai sus*/
            for (Class aClass : classes) {

                if (aClass.isAnnotationPresent(MyController.class)) {
                    Annotation anot = aClass.getAnnotation(MyController.class);

                    Method[] methods = aClass.getDeclaredMethods();

                    for (Method met : methods)
                        if (met.isAnnotationPresent(MyRequestMethod.class)) {
                            MyRequestMethod ann = met.getAnnotation(MyRequestMethod.class);
                            MethodAttributes ma = new MethodAttributes();

                            ma.setControllerClass(aClass.getName());
                            ma.setMethodName(met.getName());
                            ma.setMethodType(ann.methodType());
                            ma.setMethodParametersType(met.getParameterTypes());
                            hashMapReqs.put(((MyController) anot).urlPath() + ann.urlPath(), ma);
                        }
                }

            }
            System.out.println(hashMapReqs);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp,"GET");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp,"GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, "POST");
    }

    private void dispatchReply(HttpServletRequest req, HttpServletResponse resp, String httpMethod) throws IOException {

        /* dispatch */
        Object res = dispatch(req, resp, httpMethod);
        
        /* reply */
        
        reply(res, req, resp);
        /* catch errors */
        Exception ex = null;
        sendExceptions(ex, req, resp);
    }

    /* Where an application controller should be called */
    private Object dispatch(HttpServletRequest req, HttpServletResponse resp, String httpMethod) {

        /* pentru /test => hello*/
        /* pentru /employee => allEmployees de la Application Controller*/
        /* path-ul din request face abstractie de numele aplicatiei cu care a fost deployat serverul
        * si face abstractie de configurarea din web.xml (deci in path ramane cce e dupa /mvc */

        String pathInfo = req.getPathInfo();
/*
         if(pathInfo.startsWith("/employee")) {
             EmployeeController empController = new EmployeeController();
             return empController.getAllEmployees();
         }
        if(pathInfo.startsWith("/department")) {
            DepartmentController deptController = new DepartmentController();
            return deptController.getAllDepartments();
        }

        return "Hello";
        */
        MethodAttributes val = hashMapReqs.get(pathInfo);

        if(val != null) {

            try {
                Class<?> controllerClass = Class.forName(val.getControllerClass());
                Object controllerInstance = controllerClass.newInstance();

                Method method = controllerClass.getMethod(val.getMethodName(), val.getMethodParametersType());
                /*method.
                Object[] parameters = new Object[10]; */
                //req.getP
                Parameter[] realParameters = method.getParameters();

                /* gasesc valori printre parametrii primiti in request*/

                ArrayList<String> valueParameters = new ArrayList<String>();
                for(Parameter param : realParameters)
                    valueParameters.add(req.getParameter(param.getName()));

                //valueParameters.toArray();
                return method.invoke(controllerInstance, (String[])valueParameters.toArray(new String[0]));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* used to send the view to the client */
    private void reply(Object res, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter writer = resp.getWriter();

        ObjectMapper mapper = new ObjectMapper(); //modalitate de a serializa un obiect din Java intr-un obiect de tip JSON
        String s = mapper.writeValueAsString(res);
        writer.printf(s);
    }

    private void sendExceptions(Exception ex, HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("There was an exception!");
    }

}
