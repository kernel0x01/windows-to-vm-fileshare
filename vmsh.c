#include <stdio.h>
#include <stdlib.h>

int main()
{
    // Change current directory to /mnt
    system("cd /mnt");

    // Create hgfs directory if it doesn't exist
    system("sudo mkdir -p hgfs");

    // Add entry to /etc/fstab file
    system("sudo sh -c 'echo \"vmhgfs-fuse /mnt/hgfs fuse defaults,allow_other 0 0\" >> /etc/fstab'");

    // Reboot the system to apply changes
    system("sudo reboot now");

    return 0;
}
