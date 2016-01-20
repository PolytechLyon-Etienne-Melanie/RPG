package test.rpg.engine.console.printer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CapturePane extends JPanel implements Consumer {

    private JTextArea output;

    public CapturePane() {
        setLayout(new BorderLayout());
        output = new JTextArea();
        output.setFont(new Font("Courier New", Font.PLAIN, 13));
        add(new JScrollPane(output));
    }

    @Override
    public void appendText(final String text) {
        if (EventQueue.isDispatchThread()) {
            output.append(text);
            output.setCaretPosition(output.getText().length());
        } else {

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    appendText(text);
                }
            });

        }
    }        
}
