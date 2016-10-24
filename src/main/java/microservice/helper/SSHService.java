package microservice.helper;

import com.trilead.ssh2.Connection;
import com.trilead.ssh2.SCPClient;
import com.trilead.ssh2.Session;
import com.trilead.ssh2.StreamGobbler;

import java.io.*;
import java.util.Arrays;

import static microservice.helper.SeleniumHelper.printMethodName;

public class SSHService {
    private final String hostname;
    private final String username;
    private final String password;
    private final File keyfile;
    private final String keyPassphrase;

    /* */
    public SSHService(String hostname, String username, String password, File keyfile, String keyPassphrase) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.keyfile = keyfile;
        this.keyPassphrase = keyPassphrase;

    }

    public String executeCommand(String command, String authenticationMethod) throws InterruptedException {
        printMethodName();

        Connection connection;

        if (authenticationMethod.equals("sshkey")) {
             connection = createConnectionAndLogInWithSSHKeys();
        } else if (authenticationMethod.equals("password")) {
            connection = createConnectionAndLogIn();
        } else {
            throw new RuntimeException("Authentication method not given.");
        }

        String lines = new String();
        try{

            Session session = connection.openSession();
            session.execCommand(command);
            InputStream stdout = new StreamGobbler(session.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            while (true){
                String line =  br.readLine();
                if (line == null)
                    break;
                lines = lines +line;
            }
            session.close();

        }
        catch (IOException e){
            e.printStackTrace(System.err);
            System.exit(2);
        } finally {
            connection.close();
        }
        return lines;
    }


    public String writeToShell(String command, String authenticationMethod) {
        printMethodName();

        Connection connection;

        if (authenticationMethod.equals("sshkey")) {
            connection = createConnectionAndLogInWithSSHKeys();
        } else if (authenticationMethod.equals("password")) {
            connection = createConnectionAndLogIn();
        } else {
            throw new RuntimeException("Authentication method not given.");
        }

        String lines = new String();
        try{
            Session session = connection.openSession();
            session.requestDumbPTY();
            session.startShell();
            PrintWriter out = new PrintWriter(session.getStdin());
            out.println(command+";");
            out.println("exit\n");
            out.flush();
            out.close();
            InputStream stdout = new StreamGobbler(session.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            while (true){
                String line =  br.readLine();
                if (line == null)
                    break;
                lines = lines +"\n"+ line;
//                System.out.println(line);
            }
            session.close();

        }
        catch (IOException e){
            e.printStackTrace(System.err);
            System.exit(2);
        } finally {
            connection.close();
        }
        return lines;
    }


    /*Download file from server, example:
    SSHServiceOld sshService = new SSHServiceOld("<host/ip>","<user>", "<passwd>");
    sshService.downloadFile("/home/omaeladm/xstartup","/Users/jaheikki");
    Suggesting still to use downloadFiles method*/
    public void downloadFile(String authenticationMethod, String remoteFile,String localFolder) {
        printMethodName();

        Connection connection;

        if (authenticationMethod.equals("sshkey")) {
            connection = createConnectionAndLogInWithSSHKeys();
        } else if (authenticationMethod.equals("password")) {
            connection = createConnectionAndLogIn();
        } else {
            throw new RuntimeException("Authentication method not given.");
        }

        SCPClient client=new SCPClient(connection);
        try {
            client.get(remoteFile, localFolder);
        } catch (IOException e) {
            throw new RuntimeException("Failed to download file: " + remoteFile + " to " + localFolder + " from " + connection.getHostname(), e);
        } finally {
            connection.close();
        }
    }

    /**Download file or files, with default permision: 0600, examples
    *String localFolder = "/Users/jaheikki";
    *downloadFiles(localFolder, "test1.txt");
    *downloadFiles(localFolder, "test1.txt", "test2.txt");
    *downloadFiles(localFolder, "test1.txt", "test2.txt", "test3.txt");
    *String[] files = new String[] {"test1.txt", "test2.txt", "test3.txt"};
    downloadFiles(localFolder, files);
    * Note: "..." notation creates array automatically*/
    public void downloadFiles( String authenticationMethod, String localFolder,String... remoteFiles) {
        printMethodName();

        Connection connection;

        if (authenticationMethod.equals("sshkey")) {
            connection = createConnectionAndLogInWithSSHKeys();
        } else if (authenticationMethod.equals("password")) {
            connection = createConnectionAndLogIn();
        } else {
            throw new RuntimeException("Authentication method not given.");
        }

        SCPClient client=new SCPClient(connection);
        try {
            client.get(remoteFiles, localFolder);
        } catch (IOException e) {
            System.out.println("Tried to download following files:");
            Arrays.asList(remoteFiles).stream().forEach(s -> System.out.println(s));
            throw new RuntimeException("Failed to download files to " + localFolder + " from " + connection.getHostname(), e);
        } finally {
            connection.close();
        }
        System.out.println("Successfully downloaded following files to "+ localFolder + " from " + connection.getHostname());
        Arrays.asList(remoteFiles).stream().forEach(s -> System.out.println(s));

    }

    /*Upload single file to server allowing to rename transferred files. Otherwise proposing to use uploadFiles methods*/
    public void uploadFile(String localFile,String remoteFile,String remoteFolder,String mode, String authenticationMethod) {
        printMethodName();

        Connection connection;

        if (authenticationMethod.equals("sshkey")) {
            connection = createConnectionAndLogInWithSSHKeys();
        } else if (authenticationMethod.equals("password")) {
            connection = createConnectionAndLogIn();
        } else {
            throw new RuntimeException("Authentication method not given.");
        }

        SCPClient client=new SCPClient(connection);
        try {
            client.put(localFile,remoteFile,remoteFolder,mode);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file: " + localFile + " to " + remoteFolder + " to " + connection.getHostname(), e);
        } finally {
            connection.close();
        }
    }

    /**Upload file or files, with default permision: 0600, examples
    *uploadFiles(remoteFolder, "test1.txt");
    *uploadFiles(remoteFolder, "test1.txt", "test2.txt");
    *uploadFiles(remoteFolder, "test1.txt", "test2.txt", "test3.txt");
    *String[] files = new String[] {"test1.txt", "test2.txt", "test3.txt"};
    *uploadFiles(remoteFolder, files);
    * Note: "..." notation creates array automatically*/
    public void uploadFiles( String authenticationMethod, String remoteFolder, String... localFiles) {
        printMethodName();

        Connection connection;

        if (authenticationMethod.equals("sshkey")) {
            connection = createConnectionAndLogInWithSSHKeys();
        } else if (authenticationMethod.equals("password")) {
            connection = createConnectionAndLogIn();
        } else {
            throw new RuntimeException("Authentication method not given.");
        }

        SCPClient client=new SCPClient(connection);
        try {
            client.put(localFiles,remoteFolder);
        } catch (IOException e) {
            System.out.println("Tried to upload following files:");
            Arrays.asList(localFiles).stream().forEach(s -> System.out.println(s));
            throw new RuntimeException("Failed to upload files to " + remoteFolder + " to " + connection.getHostname(), e);
        } finally {
            connection.close();
        }
        System.out.println("Successfully uploaded following files to "+ remoteFolder + " to " + connection.getHostname());
        Arrays.asList(localFiles).stream().forEach(s -> System.out.println(s));
    }
    /**Upload file or files, with custom permision (mode), examples
    *String remoteFolder = "/foo/bar";
    *uploadFiles(remoteFolder, "0755" ,"test1.txt");
    *uploadFiles(remoteFolder, "0755", "test1.txt", "test2.txt");
    *uploadFiles(remoteFolder, "0755", "test1.txt", "test2.txt", "test3.txt");
    *String[] files = new String[] {"test1.txt", "test2.txt", "test3.txt"};
    *uploadFiles(remoteFolder, "0755", files);*/
    public void uploadFilesWithPermission(String authenticationMethod, String remoteFolder,String mode, String... localFiles) {
        printMethodName();

        Connection connection;

        if (authenticationMethod.equals("sshkey")) {
            connection = createConnectionAndLogInWithSSHKeys();
        } else if (authenticationMethod.equals("password")) {
            connection = createConnectionAndLogIn();
        } else {
            throw new RuntimeException("Authentication method not given.");
        }

        SCPClient client=new SCPClient(connection);
        try {
            client.put(localFiles,remoteFolder,mode);
        } catch (IOException e) {
            System.out.println("Tried to upload following files:");
            Arrays.asList(localFiles).stream().forEach(s -> System.out.println(s));
            throw new RuntimeException("Failed to upload files to " + remoteFolder + " to " + connection.getHostname(), e);

        } finally {
            connection.close();
        }
        System.out.println("Successfully uploaded following files to "+ remoteFolder + " to " + connection.getHostname());
        Arrays.asList(localFiles).stream().forEach(s -> System.out.println(s));

    }

    private Connection createConnectionAndLogIn() {
        Connection connection = new Connection(hostname);
        try {
            connection.connect();
            boolean isAuthenticated = connection.authenticateWithPassword(username,password);
            if (isAuthenticated == false){
                throw new IOException("Authentication failed");
            }
            return connection;
        }
        catch (IOException e){
            e.printStackTrace(System.err);
            System.exit(2);
        }
        throw new RuntimeException("Failed to create password authenticated SSH connection to:" + hostname);
    }

    private Connection createConnectionAndLogInWithSSHKeys() {
        Connection connection = new Connection(hostname);
        try {
            connection.connect();
            boolean isAuthenticated = connection.authenticateWithPublicKey(username, keyfile, keyPassphrase);
            if (isAuthenticated == false){
                throw new IOException("Authentication failed");
            }
            return connection;
        }
        catch (IOException e){
            e.printStackTrace(System.err);
            System.exit(2);
        }
        throw new RuntimeException("Failed to create SSH key authenticated connection to:" + hostname);
    }
}
