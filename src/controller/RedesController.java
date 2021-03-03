package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class RedesController {

    private Runtime runtime;

    public RedesController() {
        runtime = Runtime.getRuntime();
    }

    private boolean isSystem(String name, String operationalSystem) {
        return operationalSystem.equals(name);
    }

    private boolean isWindows(String operationalSystem) {
        return isSystem("Windows 10", operationalSystem);
    }

    private boolean isLinux(String operationalSystem) {
        return isSystem("Linux", operationalSystem);
    }

    public void ip(String operationalSystem) {
        String adaptersAndIps = "";

        if (isWindows(operationalSystem)) adaptersAndIps = windowsIp();
        if (isLinux(operationalSystem)) adaptersAndIps = linuxIp();

        System.out.println(adaptersAndIps);
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
                result.append(line).append('\n');
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

    private boolean matchEnd(String pattern, String text) {
        return Pattern.compile(pattern).matcher(text).find();
    }

    private boolean matchEndIpv4(String text) {
        return matchEnd("[0-9]$", text);
    }

    private String windowsIp() {
        return call("ipconfig")
            .lines()
            .filter(line -> line.contains("Ethernet") || line.contains("IPv4"))
            .reduce(
                "",
                (output, line) ->
                    (matchEndIpv4(output) && matchEndIpv4(line))
                        ? output
                        : output + line + "\n"
            );
    }

    private String linuxIp() {
        var ipconfig = call("ifconfig");
        return ipconfig;
    }
}
