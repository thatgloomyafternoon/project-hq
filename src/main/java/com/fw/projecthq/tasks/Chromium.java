package com.fw.projecthq.tasks;

import com.fw.projecthq.CommandExecutor;
import com.fw.projecthq.Constants;

public class Chromium extends Task {

  public Chromium(String label, CommandExecutor commandExecutor) {
    super(label, commandExecutor);
  }

  @Override
  public void execute(String password) {
    commandExecutor.setCommands("/bin/bash", "-c",
        "echo \"" + password + "\" | sudo -S /usr/bin/dpkg -l | grep chromium");
    String response = commandExecutor.run();
    boolean isChromiumBrowser = response.contains(" chromium-browser ");
    if (isChromiumBrowser) {
      setImage(Constants.IMG_SUCCESS);
    } else {
      setImage(Constants.IMG_FAILED);
    }
    repaint();
  }

}
