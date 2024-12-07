package com.jvax;

import com.jvax.configs.factories.DaoFactory;
import com.jvax.controllers.DoacaoController;
import com.jvax.controllers.DoadorController;
import com.jvax.daos.DoacaoDao;
import com.jvax.daos.DoadorDao;
import com.jvax.views.DoacaoView;
import com.jvax.views.DoadorView;
import com.jvax.views.SystemView;

public class Main {
    public static void main(String[] args) {
        DaoFactory daoFactory = new DaoFactory();
        try {
            daoFactory.openConnection();

            DoadorDao doadorDao = daoFactory.createDoadorDao();
            DoadorController doadorController = new DoadorController(doadorDao);
            DoadorView doadorView = new DoadorView(doadorController);
    
            DoacaoDao doacaoDao = daoFactory.createDoacaoDao();
            DoacaoController doacaoController = new DoacaoController(doacaoDao);
            DoacaoView doacaoView = new DoacaoView(doacaoController);
    
            SystemView systemView = new SystemView(doadorView, doacaoView);
    
            systemView.menu();
        } catch (Exception e) {
            System.out.print("Message: ");
            System.out.println(e.getMessage());

            System.out.print("Cause: ");
            System.out.println(e.getCause());

            e.printStackTrace();
        }
    }
}
