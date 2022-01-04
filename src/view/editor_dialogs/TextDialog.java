package view.editor_dialogs;

import view.MainFrame;
import view.workspaceView.SlotView;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextDialog extends AbstractDialog {
    private JToolBar toolBarUp;
    private JButton bold;
    private JButton italic;
    private JButton underline;

    private boolean boldEnabled = false;
    private boolean italicEnabled = false;
    private boolean underlineEnabled = false;

    private SimpleAttributeSet attributeSet;

    private JTextPane jTextPane;

    public TextDialog(MainFrame mainFrame) {
        super(mainFrame);
        setSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/ 4, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/ 4));
        setTitle("Text editor");
        initializeToolbar();
        add(toolBarUp, BorderLayout.NORTH);
        add(jTextPane, BorderLayout.CENTER);
    }

    public void setModel(SlotView slotView){
        slotView.getSlotHandler().readContent();
        boldEnabled = false;
        italicEnabled = false;
        underlineEnabled = false;
    }

    public Object getData(){
        return jTextPane;
    }

    private void initializeToolbar(){
        toolBarUp = new JToolBar();
        toolBarUp.setFloatable(false);
        jTextPane = new JTextPane();
        attributeSet = new SimpleAttributeSet();

        bold = new JButton();
        settingBold();

        italic = new JButton();
        settingItalic();

        underline = new JButton();
        settingUnderline();

        toolBarUp.add(bold);
        toolBarUp.add(italic);
        toolBarUp.add(underline);
        toolBarUp.add(MainFrame.getInstance().getActionManager().getSaveSlotChangeAction());
    }

    private void settingBold(){
        bold.setIcon(new ImageIcon("src/Controller/images/bold1.png"));
        bold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (boldEnabled)
                {
                    boldEnabled = false;
                    bold.setIcon(new ImageIcon("src/Controller/images/bold1.png"));
                }
                else
                {
                    boldEnabled = true;
                    bold.setIcon(new ImageIcon("src/Controller/images/bold2.png"));
                }
                StyleConstants.setBold(attributeSet, boldEnabled);
                jTextPane.setCharacterAttributes(attributeSet,true);
            }
        });
    }

    private void settingItalic(){
        italic.setIcon(new ImageIcon("src/Controller/images/italic1.png"));
        italic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (italicEnabled)
                {
                    italicEnabled = false;
                    italic.setIcon(new ImageIcon("src/Controller/images/italic1.png"));
                }
                else
                {
                    italicEnabled = true;
                    italic.setIcon(new ImageIcon("src/Controller/images/italic2.png"));
                }
                StyleConstants.setItalic(attributeSet, italicEnabled);
                jTextPane.setCharacterAttributes(attributeSet,true);
            }
        });
    }

    private void settingUnderline(){
        underline.setIcon(new ImageIcon("src/Controller/images/underline1.png"));
        underline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (underlineEnabled)
                {
                    underlineEnabled = false;
                    underline.setIcon(new ImageIcon("src/Controller/images/underline1.png"));
                }
                else
                {
                    underlineEnabled = true;
                    underline.setIcon(new ImageIcon("src/Controller/images/underline2.png"));
                }
                StyleConstants.setUnderline(attributeSet, underlineEnabled);
                jTextPane.setCharacterAttributes(attributeSet,true);
            }
        });
    }

    public JTextPane getjTextPane() {
        return jTextPane;
    }
}
