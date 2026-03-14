package com.fw.projecthq.tasks;

import com.fw.projecthq.CommandExecutor;
import com.fw.projecthq.Constants;

public class Curl extends Task {

  public Curl(String label, CommandExecutor commandExecutor) {
    super(label, commandExecutor);
  }

  @Override
  public void execute(String password) {
    commandExecutor.setCommands("/bin/bash", "-c", "echo \"" + password + "\" | sudo -S /usr/bin/dpkg -l | grep curl");
    String response = commandExecutor.run();
    String[] responses = response.split("\n");
    boolean atLeastOneEntry = responses.length >= 1;
    boolean correctDescription = response.contains("command line tool for transferring data with URL syntax");
    boolean isCurl = response.contains(" curl ");
    if (atLeastOneEntry && correctDescription && isCurl) {
      setImage(Constants.IMG_SUCCESS);
    } else {
      setImage(Constants.IMG_FAILED);
    }
    repaint();
  }

}
