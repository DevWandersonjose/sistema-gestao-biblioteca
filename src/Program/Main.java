package Program;

import View.TelaFront;
import javax.swing.*;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("Erro ao aplicar tema.");
        }

        SwingUtilities.invokeLater(() -> {
            TelaFront tela = new TelaFront();
            tela.setVisible(true);
        });
    }
}