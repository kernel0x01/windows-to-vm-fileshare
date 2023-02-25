import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    // Change current directory to /mnt
    val cdProcess = ProcessBuilder("cd", "/mnt").start()
    cdProcess.waitFor()
    if (cdProcess.exitValue() != 0) {
        println("Error changing directory: ${cdProcess.getErrorStream().bufferedReader().readText()}")
        return
    }

    // Create hgfs directory if it doesn't exist
    val mkdirProcess = ProcessBuilder("sudo", "mkdir", "-p", "hgfs").start()
    mkdirProcess.waitFor()
    if (mkdirProcess.exitValue() != 0) {
        println("Error creating directory: ${mkdirProcess.getErrorStream().bufferedReader().readText()}")
        return
    }

    // Add entry to /etc/fstab file
    val echoCommand = "echo 'vmhgfs-fuse /mnt/hgfs fuse defaults,allow_other 0 0' | sudo tee -a /etc/fstab"
    val echoProcess = ProcessBuilder("/bin/sh", "-c", echoCommand).start()
    echoProcess.waitFor()
    if (echoProcess.exitValue() != 0) {
        println("Error adding entry to /etc/fstab: ${echoProcess.getErrorStream().bufferedReader().readText()}")
        return
    }

    // Reboot the system to apply changes
    val rebootProcess = ProcessBuilder("sudo", "reboot", "now").start()
    rebootProcess.waitFor()
    if (rebootProcess.exitValue() != 0) {
        println("Error rebooting system: ${rebootProcess.getErrorStream().bufferedReader().readText()}")
        return
    }

    println("Successfully created hgfs folder and added entry to /etc/fstab. System reboot initiated.")
}
