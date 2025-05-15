package crudmvc;

import Controlador.CtrlFuncionario;
import Modelo.ConsultasFuncionario;
import Modelo.Funcionario;
import Vista.frmFuncionario;

/**
 *
 * @author MRoblesDev
 */
public class CRUDMVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Funcionario mod = new Funcionario();
        ConsultasFuncionario modC = new ConsultasFuncionario();
        frmFuncionario frm = new frmFuncionario();

        CtrlFuncionario ctrl = new CtrlFuncionario(mod, modC, frm);
        ctrl.iniciar();
        frm.setVisible(true);
    }
}
