package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RedesController {

    private Runtime runtime;

    public RedesController() {
        runtime = Runtime.getRuntime();
    }

    public void ip(String operationalSystem) {
        if (operationalSystem.equals("Windows 10")) windowsIp();
        if (operationalSystem.equals("Linux")) linuxIp();
    }

    private String call(String process) {
        try {
            var runningProcess = runtime.exec(process);
            var stream = runningProcess.getInputStream();
            var reader = new InputStreamReader(stream);
            var buffer = new BufferedReader(reader);
            var line = buffer.readLine();
            var result = new StringBuilder(line);

            while (line != null) {
                line = buffer.readLine();
                result.append(line);
            }

            stream.close();
            reader.close();
            buffer.close();

            return result.toString();
        } catch (IOException e) {
            System.err.println(
                "Você digitou o comando \"" +
                process +
                "\" de maneira incorreta, ou o Java não possui permissão para executar tal processo."
            );
        }

        return "";
    }

    private void windowsIp() {
        var ipconfig = call("ipconfig");
        System.out.println(ipconfig);
    }

    private void linuxIp() {
        var ipconfig = call("ifconfig");
        System.out.println(ipconfig);
    }
}
