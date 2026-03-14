package com.fw.projecthq.tasks;

import com.fw.projecthq.CommandExecutor;
import com.fw.projecthq.Constants;

public class AndroidStudio extends Task {

  public AndroidStudio(String label, CommandExecutor commandExecutor) {
    super(label, commandExecutor);
  }

  @Override
  public void execute(String password) {
    commandExecutor.setCommands("/bin/bash", "-c", "/usr/local/android-studio/bin/studio --version");
    String response = commandExecutor.run();
    boolean androidStudioInstalled = response.contains("Android Studio Ladybug Feature");
    if (androidStudioInstalled) {
      setImage(Constants.IMG_SUCCESS);
    } else {
      setImage(Constants.IMG_FAILED);
    }
    repaint();
  }

}
