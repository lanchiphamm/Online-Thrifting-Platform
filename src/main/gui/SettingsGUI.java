package gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import model.EventLog;
import model.User;

/***
 * Represents the Settings tab, one of the 3 tabs of the main frame.
 * Allow users to log out of the platform with a choice of saving all
 * their previous activities or not.
 */
public class SettingsGUI {
    JPanel settings;
    JRadioButton yes;
    JRadioButton no;
    Boolean save;
    JButton logOut;

    LoginGUI loginGUI;
    HomePageGUI hp;

    // MODIFIES: this
    // EFFECTS: Constructs Settings tab for user to log out of application
    public SettingsGUI(LoginGUI loginGUI, HomePageGUI hp) {
        this.loginGUI = loginGUI;
        this.hp = hp;

        settings = new JPanel();
        settings.setLayout(new BoxLayout(settings, BoxLayout.PAGE_AXIS));
        settings.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel settingsLabel = new JLabel("Settings");
        settings.add(settingsLabel);

        JLabel question = new JLabel("Do you want to save your account information to our platform?");
        question.setBounds(20, 100, 80, 25);
        settings.add(question);

        loadOptions();
        loadLogOutButton();
    }

    // MODIFIES: this
    // EFFECTS: load options when user log out
    public void loadOptions() {
        yes = new JRadioButton(new AbstractAction("Yes") {
            @Override
            public void actionPerformed(ActionEvent e) {
                save = true;
            }
        });
        yes.setMnemonic(KeyEvent.VK_Y);
        yes.setActionCommand("yes");
        settings.add(yes);

        no = new JRadioButton(new AbstractAction("No") {
            @Override
            public void actionPerformed(ActionEvent e) {
                save = false;
            }
        });
        no.setMnemonic(KeyEvent.VK_N);
        no.setActionCommand("no");
        settings.add(no);

        ButtonGroup group = new ButtonGroup();
        group.add(yes);
        group.add(no);
    }

    // MODIFIES: this
    // EFFECTS: display log out button and handle when button is chose -> go back to log in frame
    public void loadLogOutButton() {
        logOut = new JButton(new AbstractAction("Log out") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!save) {
                    User currentUser = loginGUI.getUser();
                    loginGUI.getPlatform().removeUser(currentUser);
                }
                loginGUI.setUser(null);
                loginGUI.savePlatform();
                LoginGUI.printLog(EventLog.getInstance());
                hp.hideFrame();
                new LoginGUI();
            }
        });
        settings.add(logOut);
    }

    public JPanel loadSettingsTab() {
        return settings;
    }
}
