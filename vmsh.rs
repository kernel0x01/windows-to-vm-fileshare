use std::process::Command;

fn main() {
    // Change current directory to /mnt
    Command::new("/bin/bash")
            .arg("-c")
            .arg("cd /mnt")
            .output()
            .expect("failed to change directory to /mnt");

    // Create hgfs directory if it doesn't exist
    Command::new("/bin/bash")
            .arg("-c")
            .arg("sudo mkdir -p /mnt/hgfs")
            .output()
            .expect("failed to create hgfs directory");

    // Add entry to /etc/fstab file
    Command::new("/bin/bash")
            .arg("-c")
            .arg("echo 'vmhgfs-fuse /mnt/hgfs fuse defaults,allow_other 0 0' | sudo tee -a /etc/fstab")
            .output()
            .expect("failed to add entry to /etc/fstab file");

    // Reboot the system to apply changes
    Command::new("/bin/bash")
            .arg("-c")
            .arg("sudo reboot now")
            .spawn()
            .expect("failed to reboot the system");
}
