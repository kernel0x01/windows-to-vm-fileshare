import os

fn main() {
    // Change current directory to /mnt
    cmd1 := os.exec('cd /mnt')

    // Create hgfs directory if it doesn't exist
    cmd2 := os.exec('sudo mkdir -p /mnt/hgfs')

    // Add entry to /etc/fstab file
    cmd3 := os.exec("echo 'vmhgfs-fuse /mnt/hgfs fuse defaults,allow_other 0 0' | sudo tee -a /etc/fstab")

    // Reboot the system to apply changes
    cmd4 := os.exec('sudo reboot now')
}
