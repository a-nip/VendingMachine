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
    public static void main(String[] args) {
        // Instantiate DAO
        VendingMachineDao vendingMachineDao = new VendingMachineDaoImpl();

        // Instantiate Service Layer
        VendingMachineServiceLayer vendingMachineService = new VendingMachineServiceLayerImpl(vendingMachineDao);

        // Instantiate View and Controller
        UserIOConsoleImpl userIO = new UserIOConsoleImpl();
        VendingMachineView vendingMachineView = new VendingMachineView(userIO);
        VendingMachineController vendingMachineController = new VendingMachineController(vendingMachineService, vendingMachineView);

        // Start the application
        vendingMachineController.run();
    }
}