package com.fw.projecthq;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class CommandExecutor {

  private String[] cmds;
  private Process process;
  private BufferedReader bufferedReader;

  public CommandExecutor() {
  }

  public void setCommands(String... cmds) {
    this.cmds = cmds;
  }

  public String run() {
    try {
      ProcessBuilder pb = new ProcessBuilder(cmds);
      pb.redirectErrorStream(true);
      pb.environment().put("PATH", System.getenv("PATH"));
      process = pb.start();
      bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line = "";
      String response = "";
      while ((line = bufferedReader.readLine()) != null) {
        response += line + "\n";
      }
      process.waitFor();
      bufferedReader.close();
      process.destroy();
      return response;
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      return null;
    }
  }

}
