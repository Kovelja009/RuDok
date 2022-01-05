package controller.slotHandler;

import view.MainFrame;
import view.editor_dialogs.TextDialog;
import view.workspaceView.SlotView;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class TextHandler extends SlotHandler{

    public TextHandler(SlotView slotView) {
        super(slotView);
    }

    @Override
    public String readContent() {
        boolean isB = false;
        boolean isIt = false;
        boolean isUn = false;

        StyledDocument styledDocument = new DefaultStyledDocument();

        for(int i = 0; i < slotView.getSlot().getText().length(); i++){
            char ch = slotView.getSlot().getText().charAt(i);

            if(ch == MainFrame.getInstance().getCharList().get(0))
                isB = !isB;
            else if(ch == MainFrame.getInstance().getCharList().get(1))
                isIt = !isIt;
            else if(ch == MainFrame.getInstance().getCharList().get(2))
                isUn = !isUn;
            else{
                SimpleAttributeSet smp = new SimpleAttributeSet();
                StyleConstants.setBold(smp, isB);
                StyleConstants.setItalic(smp, isIt);
                StyleConstants.setUnderline(smp, isUn);
                try {
                    styledDocument.insertString(styledDocument.getLength(),"" + ch, smp);
                }catch (BadLocationException e){
                    System.out.println("Error  - writting style");
                }

            }
        }

        (((TextDialog)MainFrame.getInstance().getCurrentDialog()).getjTextPane()).setStyledDocument(styledDocument);

        return "";
    }

    @Override
    public void writeContent(Object content) {
        JTextPane jTextPane = (JTextPane) content;

        String data = "";
        StyledDocument styledDocument = jTextPane.getStyledDocument();
        boolean isBold = false;
        boolean isItalic = false;
        boolean isUnderline = false;
        for(int i = 0; i < jTextPane.getText().length(); i++){
            isBold = false;
            isItalic = false;
            isUnderline = false;
            if(styledDocument.getCharacterElement(i).getAttributes().getAttribute(StyleConstants.Bold) != null)
                isBold = (boolean)styledDocument.getCharacterElement(i).getAttributes().getAttribute(StyleConstants.Bold);

            if(styledDocument.getCharacterElement(i).getAttributes().getAttribute(StyleConstants.Italic) != null)
                isItalic = (boolean)styledDocument.getCharacterElement(i).getAttributes().getAttribute(StyleConstants.Italic);

            if(styledDocument.getCharacterElement(i).getAttributes().getAttribute(StyleConstants.Underline) != null)
                isUnderline = (boolean)styledDocument.getCharacterElement(i).getAttributes().getAttribute(StyleConstants.Underline);

            String newChar = "" + jTextPane.getText().charAt(i);

            if(isBold)
                newChar = MainFrame.getInstance().getCharList().get(0) + newChar + MainFrame.getInstance().getCharList().get(0);
            if(isItalic)
                newChar = MainFrame.getInstance().getCharList().get(1) + newChar + MainFrame.getInstance().getCharList().get(1);
            if(isUnderline)
                newChar = MainFrame.getInstance().getCharList().get(2) + newChar + MainFrame.getInstance().getCharList().get(2);

            data += newChar;
        }
        System.out.println(data);
        slotView.getSlot().setText(data);
    }

    @Override
    public void paint(Graphics g) {
//        g.setFont(new Font("Ariel", Font.ITALIC, 25));
//        int old_posX = slotView.getX() + 10;
//        int old_posY = slotView.getY() + 10;
//        g.drawString(slotView.getSlot().getText(), old_posX, old_posY);
//        g.setFont(new Font("Ariel", Font.ITALIC, 7));
//        g.drawString("proba", old_posX + 7*slotView.getSlot().getText().length(), old_posY);
        boolean isB = false;
        boolean isIt = false;
        boolean isUn = false;

        int x = slotView.getX();
        int y = slotView.getY() + 10;

        for(int i = 0; i < slotView.getSlot().getText().length(); i++){
            char ch = slotView.getSlot().getText().charAt(i);

            if(ch == MainFrame.getInstance().getCharList().get(0))
                isB = !isB;
            else if(ch == MainFrame.getInstance().getCharList().get(1))
                isIt = !isIt;
            else if(ch == MainFrame.getInstance().getCharList().get(2))
                isUn = !isUn;
            else{
                Font font = null;
                if(isB && isIt && isUn){
                    Map<TextAttribute, Integer> mapa = new HashMap<>();
                    mapa.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    font = new Font("Ariel", Font.ITALIC | Font.BOLD, 11).deriveFont(mapa);
                }
                else if(isB && isUn){
                    Map<TextAttribute, Integer> mapa = new HashMap<>();
                    mapa.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    font = new Font("Ariel", Font.BOLD, 11).deriveFont(mapa);
                }
                else if(isIt && isUn){
                    Map<TextAttribute, Integer> mapa = new HashMap<>();
                    mapa.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    font = new Font("Ariel", Font.ITALIC, 11).deriveFont(mapa);
                }
                else if(isB && isIt){
                    font = new Font("Ariel", Font.ITALIC | Font.BOLD, 11);
                }

                else if(isUn){
                    Map<TextAttribute, Integer> mapa = new HashMap<>();
                    mapa.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    font = new Font("Ariel", Font.PLAIN, 11).deriveFont(mapa);
                }
                else if(isB){
                    font = new Font("Ariel", Font.BOLD, 11);
                }
                else if(isIt){
                    font = new Font("Ariel", Font.ITALIC, 11);
                }else{
                    font = new Font("Ariel", Font.PLAIN, 11);
                    x+=1;
                }

                x += 5;
                g.setFont(font);
                g.drawString(""+ ch, x, y);
            }
        }
    }
}
