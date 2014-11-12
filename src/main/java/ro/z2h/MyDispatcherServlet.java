package ro.z2h;

import ro.z2h.annotation.MyController;
import ro.z2h.controller.DepartmentController;
import ro.z2h.controller.EmployeeController;
import ro.z2h.fmk.AnnotationScanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.Iterator;

public class MyDispatcherServlet extends HttpServlet{

    @Override
    public void init() throws ServletException {


        try {

            Iterable<Class> classes = AnnotationScanUtils.getClasses("ro.z2h.controller");

            /* cu shortcut "iter" genereaza for pentru iteratorul de mai sus*/
            for (Class aClass : classes) {

                if (aClass.isAnnotationPresent(MyController.class)) {
                    Annotation anot = aClass.getAnnotation(MyController.class);
                    System.out.println(((MyController)anot).urlPath());
                }
            //
               // if (anot != null)

            }
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

         if(pathInfo.startsWith("/employee")) {
             EmployeeController empController = new EmployeeController();
             return empController.getAllEmployees();
         }
        if(pathInfo.startsWith("/department")) {
            DepartmentController deptController = new DepartmentController();
            return deptController.getAllDepartments();
        }

        return "Hello";
    }

    /* used to send the view to the client */
    private void reply(Object res, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter writer = resp.getWriter();

        writer.printf(res.toString());
    }

    private void sendExceptions(Exception ex, HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("There was an exception!");
    }

}
