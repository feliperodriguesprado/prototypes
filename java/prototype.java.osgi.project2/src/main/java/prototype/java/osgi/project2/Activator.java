package prototype.java.osgi.project2;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Start bundle prototype.java.osgi.project1-v1-PROTOTYPE");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        MessagePrinter printer = applicationContext.getBean(MessagePrinter.class);
        printer.printMessage();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stop bundle prototype.java.osgi.project1-v1-PROTOTYPE");
    }

}
