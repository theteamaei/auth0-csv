package com.auth0.trf.swing.ui;

import com.auth0.trf.model.dto.UserDto;
import com.auth0.trf.util.AuthUtil;
import com.auth0.trf.util.CommonUtil;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    JTextField fileName = new JTextField();
    JLabel status = new JLabel("Please don't exit the application while in progress. This will take a few minutes.");
    JTextField connectionName = new JTextField("IELTSDatabase-???");

    public MainFrame() {
        this.setTitle("Auth0-CSV Account Generator");
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(184,0,54));

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);

        JPanel topCenter = new JPanel();
        topCenter.setBackground(Color.WHITE);

        JPanel bottomCenter = new JPanel();
        bottomCenter.setBackground(Color.WHITE);
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBackground(new Color(184,0,54));
//        statusBar.setBorder(
//                new CompoundBorder(
//                        new LineBorder(Color.DARK_GRAY),
//                        new EmptyBorder(4, 4, 4, 4)));

        JLabel title = new JLabel("This application will generate a folder containing accounts in your Desktop.");
        title.setFont(new Font("Calibri", Font.BOLD, 13));
        title.setForeground(Color.WHITE);
        topPanel.add(title);

        status.setForeground(Color.WHITE);
        status.setFont(new Font("Calibri", Font.BOLD, 12));
        statusBar.add(status);

        fileName.setEditable(false);
        fileName.setPreferredSize(new Dimension( 200, 24 ) );
        topCenter.add(fileName);
        JButton findFileButton = new JButton("Open");
        findFileButton.setBackground(new Color(184, 0, 54));
        findFileButton.setForeground(Color.WHITE);
        findFileButton.setFocusPainted(false);
        findFileButton.setFont(new Font("Calibri", Font.BOLD, 13));
        findFileButton.addActionListener(new OpenFile());
        topCenter.add(findFileButton);

        JButton generateButton = new JButton("Generate");
        generateButton.setBackground(new Color(184, 0, 54));
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        generateButton.setFont(new Font("Calibri", Font.BOLD, 13));
        generateButton.addActionListener(new GenerateFile());
        topCenter.add(generateButton);

        JLabel jLabel = new JLabel("Auth0 Connection:");
        jLabel.setFont(new Font("Calibri", Font.BOLD, 14));

        connectionName.setFont(new Font("Calibri", Font.BOLD, 12));
        connectionName.setPreferredSize(new Dimension(253, 24));
        bottomCenter.add(jLabel);
        bottomCenter.add(connectionName);


        centerPanel.add(BorderLayout.NORTH, topCenter);
        centerPanel.add(BorderLayout.CENTER, bottomCenter);


        this.add(BorderLayout.NORTH, topPanel);
        this.add(BorderLayout.CENTER, centerPanel);
        this.add(BorderLayout.SOUTH, statusBar);
        this.pack();
    }

    private class GenerateFile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                status.setText("Reading CSV Document...");
                AuthUtil authUtil = new AuthUtil();
                List<UserDto> userDtoList = authUtil.getAccountList(fileName.getText(), connectionName.getText());
                if (userDtoList != null && !userDtoList.isEmpty()) {
                    status.setText("Generating account list...");
                    List<List<UserDto>> partitionList = authUtil.partitionAccountList(userDtoList);
                    List<String> accountList = new ArrayList<>();
                    for (List<UserDto> userDtos : partitionList) {
                        String jsonQuery = CommonUtil.toMinifyJson(userDtos);
                        accountList.add(jsonQuery);
                    }
                    int result = CommonUtil.generateFiles(accountList);
                    status.setText("Files generated: " + result);
                } else {
                    status.setText("Unable to generate account list...");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private class OpenFile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jFileChooser = new JFileChooser();
            int rVal = jFileChooser.showSaveDialog(MainFrame.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                fileName.setText(jFileChooser.getCurrentDirectory().toString() + "/" + jFileChooser.getSelectedFile().getName());
            }

            if (rVal == JFileChooser.CANCEL_OPTION) {
                fileName.setText("");
            }
        }
    }
}
