package view;

import controller.RedesController;

public class Main {

    public static void main(String[] args) {
        var controller = new RedesController();
        var operationalSystem = System.getProperty("os.name");
        System.out.println(operationalSystem);
        // controller.ip(operationalSystem);
        controller.ping(operationalSystem);
    }
}
