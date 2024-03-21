package com.vendingmachine;

import com.vendingmachine.controller.VendingMachineController;
import com.vendingmachine.dao.VendingMachineDao;
import com.vendingmachine.dao.VendingMachineDaoImpl;
import com.vendingmachine.service.VendingMachineServiceLayer;
import com.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.vendingmachine.ui.UserIO;
import com.vendingmachine.ui.UserIOConsoleImpl;
import com.vendingmachine.ui.VendingMachineView;

public class App {
    // Instantiate the UserIO implementation
    UserIO io = new UserIOConsoleImpl();
    // Instantiate the View and wire the UserIO implementation into it
    VendingMachineView view = new VendingMachineView(io);
    // Instantiate the DAO
    VendingMachineDao dao = new VendingMachineDaoImpl();
    // Instantiate the Service Layer and wire the DAO into it
    VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);
    // Instantiate the Controller and wire the Service Layer into it
    VendingMachineController controller = new VendingMachineController(service, view);
    // Kick off the Controller
        controller.run();
}
