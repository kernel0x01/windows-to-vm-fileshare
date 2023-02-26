package main

import (
    "os"
    "os/exec"
)

func main() {
    // Change current directory to /mnt
    cmd1 := exec.Command("/bin/bash", "-c", "cd /mnt")
    cmd1.Run()

    // Create hgfs directory if it doesn't exist
    cmd2 := exec.Command("/bin/bash", "-c", "sudo mkdir -p /mnt/hgfs")
    cmd2.Run()

    // Add entry to /etc/fstab file
    cmd3 := exec.Command("/bin/bash", "-c", "echo 'vmhgfs-fuse /mnt/hgfs fuse defaults,allow_other 0 0' | sudo tee -a /etc/fstab")
    cmd3.Run()

    // Reboot the system to apply changes
    cmd4 := exec.Command("/bin/bash", "-c", "sudo reboot now")
    cmd4.Start()
}
