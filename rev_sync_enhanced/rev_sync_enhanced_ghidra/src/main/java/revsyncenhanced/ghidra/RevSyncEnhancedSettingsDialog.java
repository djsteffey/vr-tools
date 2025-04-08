package revsyncenhanced.ghidra;

import docking.DialogComponentProvider;
import docking.widgets.label.GLabel;
import docking.widgets.textfield.IntegerTextField;
import javax.swing.*;
import java.awt.*;

public class RevSyncEnhancedSettingsDialog extends DialogComponentProvider {
    // variables
    private IMessageLogger m_messageLogger;
    private JTextField m_usernameField;
    private JTextField m_hostField;
    private IntegerTextField m_portField;
    private JTextField m_credentialsField;

    // methods
    protected RevSyncEnhancedSettingsDialog(IMessageLogger messageLogger) {
        super("RevSyncEnhanced");

        this.m_messageLogger = messageLogger;
        this.m_usernameField = new JTextField(20);
        this.m_hostField = new JTextField(20);
        this.m_portField = new IntegerTextField(20);
        this.m_credentialsField = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        JPanel panel1 = new JPanel(new GridBagLayout());

        panel.add(new GLabel("username"), new GridBagConstraints(
                0, 0,
                1, 1,
                1, 1,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2),
                2, 2
        ));
        panel.add(this.m_usernameField, new GridBagConstraints(
                1, 0,
                1, 1,
                1, 1,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2),
                2, 2
        ));

        panel.add(new GLabel("host"), new GridBagConstraints(
                0, 1,
                1, 1,
                1, 1,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2),
                2, 2
        ));
        panel.add(this.m_hostField, new GridBagConstraints(
                1, 1,
                1, 1,
                1, 1,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2),
                2, 2
        ));

        panel.add(new GLabel("port"), new GridBagConstraints(
                0, 2,
                1, 1,
                1, 1,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2),
                2, 2
        ));
        panel.add(this.m_portField.getComponent(), new GridBagConstraints(
                1, 2,
                1, 1,
                1, 1,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2),
                2, 2
        ));

        panel.add(new GLabel("credential"), new GridBagConstraints(
                0, 3,
                1, 1,
                1, 1,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2),
                2, 2
        ));
        panel.add(this.m_credentialsField, new GridBagConstraints(
                1, 3,
                1, 1,
                1, 1,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2),
                2, 2
        ));

        this.addWorkPanel(panel);
        this.addOKButton();
        this.addCancelButton();

        // load settings
        this.loadSettings();
    }

    @Override
    protected void okCallback() {
        // save
        this.saveSettings();

        // close
        this.close();
    }

    private void loadSettings(){
        // create settings
        RevSyncEnhancedSettings settings = new RevSyncEnhancedSettings(this.m_messageLogger);

        // load settings
        if (settings.load()){
            // set fields
            this.m_usernameField.setText(settings.getUsername());
            this.m_hostField.setText(settings.getHost());
            this.m_portField.setValue(settings.getPort());
            this.m_credentialsField.setText(settings.getCredentials());
        }
    }

    private void saveSettings(){
        // create settings
        RevSyncEnhancedSettings settings = new RevSyncEnhancedSettings(this.m_messageLogger);

        // set from fields
        settings.setUsername(this.m_usernameField.getText());
        settings.setHost(this.m_hostField.getText());
        settings.setPort(this.m_portField.getIntValue());
        settings.setCredentials(this.m_credentialsField.getText());

        // save settings
        settings.save();
    }
}
