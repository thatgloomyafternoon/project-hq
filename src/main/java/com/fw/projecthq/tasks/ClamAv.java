package com.fw.projecthq.tasks;

import com.fw.projecthq.CommandExecutor;
import com.fw.projecthq.Constants;

public class ClamAv extends Task {

  public ClamAv(String label, CommandExecutor commandExecutor) {
    super(label, commandExecutor);
  }

  @Override
  public void execute(String password) {
    commandExecutor.setCommands("/bin/bash", "-c",
        "echo \"" + password + "\" | sudo -S /usr/bin/dpkg -l | grep clamav");
    String response = commandExecutor.run();
    boolean clamav = response.contains(" clamav ");
    boolean clamavBase = response.contains(" clamav-base ");
    boolean freshclam = response.contains(" clamav-freshclam ");
    boolean libclamav = response.contains("libclamav");
    if (clamav && clamavBase && freshclam && libclamav) {
      setImage(Constants.IMG_SUCCESS);
    } else {
      setImage(Constants.IMG_FAILED);
    }
    repaint();
  }

}
