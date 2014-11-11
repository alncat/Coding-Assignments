package com.seanharger.chatapp.view.roompanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class ChatroomPanel extends JPanel {

  private static final long serialVersionUID = -4520690904050470438L;

  private IViewToChatroomAdapter chatroom;

  private final JTextArea messageArea = new JTextArea();
  private final JPanel panelChatroomActions = new JPanel();
  private final JTextField textFieldMessage = new JTextField();
  private final JButton btnSend = new JButton("Send");
  private final JButton btnLeave = new JButton("Leave");
  private final JScrollPane scrollPane = new JScrollPane(messageArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

  /**
   * Create the panel.
   */
  public ChatroomPanel(IViewToChatroomAdapter chatroom) {
    this.chatroom = chatroom;
    initGUI();
  }

  public void installAdapter(IViewToChatroomAdapter chatroom) {
    this.chatroom = chatroom;
  }

  public void appendTextToFrame(String message) {
    messageArea.append(message + "\n");
    /* Scrolls down to the most recent message */
    messageArea.setCaretPosition(messageArea.getText().length());

  }


  public void initGUI() {

    setLayout(new BorderLayout(0, 0));

    add(panelChatroomActions, BorderLayout.SOUTH);
    textFieldMessage.setToolTipText("Type a message to send to chatroom.");

    textFieldMessage.setColumns(20);
    panelChatroomActions.add(textFieldMessage);
    btnSend.setToolTipText("Send message to chatroom.");
    btnSend.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        chatroom.send(textFieldMessage.getText());
        textFieldMessage.setText("");
      }
    });

    panelChatroomActions.add(btnSend);
    btnLeave.setToolTipText("Leave the chatroom.");

    panelChatroomActions.add(btnLeave);
    btnLeave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        chatroom.leave();
      }
    });

    
    add(scrollPane, BorderLayout.CENTER);    
  }

}
