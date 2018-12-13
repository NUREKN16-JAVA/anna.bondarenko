package ua.nure.kn.bondarenko.gui;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.kn.bondarenko.User;
import ua.nure.kn.bondarenko.util.Messages;

public class DetailsPanel extends JPanel implements ActionListener {

		private MainFrame parent;
		private JPanel buttonPanel;
		private JPanel fieldPanel;
		private JButton okButton;
		private JTextField AgeField;
		private JTextField lastNameField;
		private JTextField firstNameField;
		private User user;
		
		public DetailsPanel(MainFrame frame) {
			parent = frame;
			initialize();
		}

		private void initialize() {
			this.setName("detailsPanel"); //$NON-NLS-1$
			this.setLayout(new BorderLayout());
			this.add(getFieldPanel(), BorderLayout.NORTH);
			this.add(getButtonPanel(), BorderLayout.SOUTH);
		}

		private JPanel getButtonPanel() {
			if (buttonPanel == null) {
				buttonPanel = new JPanel();
				buttonPanel.add(getOkButton());
			}
			return buttonPanel;
		}

		private JButton getOkButton() {
			if (okButton == null) {
				okButton = new JButton();
				okButton.setText(Messages.getString("DetailsPanel.ok"));  //$NON-NLS-1$
				okButton.setName("okButton"); //$NON-NLS-1$
				okButton.setActionCommand("ok"); //$NON-NLS-1$
				okButton.addActionListener(this);
			}
			return okButton;
		}

		private JPanel getFieldPanel() {
			if (fieldPanel == null) {
				fieldPanel = new JPanel();
				fieldPanel.setLayout(new GridLayout(3, 2));
				addLabeledField(fieldPanel,Messages.getString("DetailsPanel.first_name"), getFirstNameField());   //$NON-NLS-1$
				addLabeledField(fieldPanel, Messages.getString("DetailsPanel.last_name"), getLastNameField());   //$NON-NLS-1$
				addLabeledField(fieldPanel, Messages.getString("DetailsPanel.age"), getAgeField());  //$NON-NLS-1$
			}
			return fieldPanel;
		}

		private JTextField getAgeField() {
			if (AgeField == null) {
				AgeField = new JTextField();
				AgeField.setName("ageField"); //$NON-NLS-1$
			}
			return AgeField;
		}

		private JTextField getLastNameField() {
			if (lastNameField == null) {
				lastNameField = new JTextField();
				lastNameField.setName("lastNameField"); //$NON-NLS-1$
			}
			return lastNameField;
		}

		private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
			JLabel label = new JLabel(labelText);
			label.setLabelFor(textField);
			panel.add(label);
			panel.add(textField);
			
		}

		private JTextField getFirstNameField() {
			if (firstNameField == null) {
				firstNameField = new JTextField();
				firstNameField.setName("firstNameField"); //$NON-NLS-1$
			}
			return firstNameField;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if ("".equalsIgnoreCase(e.getActionCommand())) //$NON-NLS-1$
				this.setVisible(false);
			this.setVisible(false);
			parent.showBrowsePanel();
		}

		  public void setUser(User user) {
		        this.user = user;
		        getFirstNameField().setText(user.getFirstName());
		        getLastNameField().setText(user.getLastName());
		        getAgeField().setText(String.valueOf(user.getAge()));
		    }
}
