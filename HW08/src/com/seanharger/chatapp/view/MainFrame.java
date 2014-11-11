package com.seanharger.chatapp.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import com.seanharger.chatapp.model.chatroom.ChatroomID;
import com.seanharger.chatapp.view.roompanel.ChatroomPanel;
import com.seanharger.chatapp.view.roompanel.IViewToChatroomAdapter;

import common.chatroom.IChatroomID;

public class MainFrame extends JFrame {

  private static final long serialVersionUID = -2286474795664030747L;
  private IViewToModelAdapter model;
  private JPanel contentPane;
  private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
  private final JPanel panelConnect = new JPanel();
  private final JButton btnNewChatroom = new JButton("New chatroom");
  private final JButton btnGetChatrooms = new JButton("Get chatrooms");
  private final JComboBox<IChatroomID> comboBoxSelectChatroom = new JComboBox<IChatroomID>();
  private final JButton btnJoin = new JButton("Join");

  public MainFrame(IViewToModelAdapter model) {
    this.model = model;
    initGUI();

  }

  public void setChatroomList(List<IChatroomID> chatroomIDs) {
    comboBoxSelectChatroom.removeAllItems();
    for (IChatroomID chatroom : chatroomIDs) {
      comboBoxSelectChatroom.addItem(chatroom);
    }
  }

  public void showInformationDialog(String title, String message) {
    JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
  }

  public void showErrorDialog(String title, String errorMessage) {
    JOptionPane.showMessageDialog(this, errorMessage, title, JOptionPane.ERROR_MESSAGE);
  }

  public boolean displayQuestionMessage(String title, String question) {
    return (JOptionPane.showConfirmDialog(this, question, title, JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION;
  }

  public void start() {
    this.setVisible(true);
  }

  public ChatroomPanel makeChatroomPanel(String name, IViewToChatroomAdapter chatroom) {
    ChatroomPanel newPanel = new ChatroomPanel(chatroom);
    tabbedPane.add(name, newPanel);
    return newPanel;
  }

  public void removeChatroomPanel(ChatroomPanel panel) {
    tabbedPane.remove(panel);
  }

  public void initGUI() {
    JFrame thisMainFrame = this;
    
    this.setSize(600, 500);
    this.setLocation(100, 100);
    this.setMinimumSize(new Dimension(600, 500));

    setTitle("ChatApp");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);

    contentPane.add(tabbedPane, BorderLayout.CENTER);

    contentPane.add(panelConnect, BorderLayout.NORTH);
    btnNewChatroom.setToolTipText("Creates a new chatroom.");
    btnNewChatroom.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String newRoomName =
            JOptionPane.showInputDialog(thisMainFrame, "Enter the name of the chatroom.",
                "Chatroom Name", JOptionPane.QUESTION_MESSAGE);
        if (newRoomName != null && newRoomName.length() > 0) {
          model.createChatroom(newRoomName);
        }
      }


    });

    panelConnect.add(btnNewChatroom);
    btnGetChatrooms.setToolTipText("Get chatrooms from a specific user.");
    btnGetChatrooms.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String ipAddress =
            JOptionPane.showInputDialog(thisMainFrame, "Enter the IP address of the remote user.",
                "Remote IP Address", JOptionPane.QUESTION_MESSAGE);
        if (ipAddress != null && ipAddress.length() > 0) {
          model.joinChatroom(ipAddress);
        }
      }
    });

    panelConnect.add(btnGetChatrooms);
    btnJoin.setToolTipText("Joins the selected chatroom.");
    btnJoin.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println(comboBoxSelectChatroom.getItemCount());
        model.requestToJoin(comboBoxSelectChatroom.getItemAt(comboBoxSelectChatroom
            .getSelectedIndex()));
      }
    });
    panelConnect.add(comboBoxSelectChatroom);

    panelConnect.add(btnJoin);
  }
}