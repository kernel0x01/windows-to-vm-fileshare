using System;

class Program
{
    static void Main(string[] args)
    {
        // Change current directory to /mnt
        System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo()
        {
            FileName = "/bin/bash",
            Arguments = "-c \"cd /mnt\"",
            UseShellExecute = false,
            CreateNoWindow = true
        }).WaitForExit();

        // Create hgfs directory if it doesn't exist
        System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo()
        {
            FileName = "/bin/bash",
            Arguments = "-c \"sudo mkdir -p /mnt/hgfs\"",
            UseShellExecute = false,
            CreateNoWindow = true
        }).WaitForExit();

        // Add entry to /etc/fstab file
        System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo()
        {
            FileName = "/bin/bash",
            Arguments = "-c \"echo 'vmhgfs-fuse /mnt/hgfs fuse defaults,allow_other 0 0' | sudo tee -a /etc/fstab\"",
            UseShellExecute = false,
            CreateNoWindow = true
        }).WaitForExit();

        // Reboot the system to apply changes
        System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo()
        {
            FileName = "/bin/bash",
            Arguments = "-c \"sudo reboot now\"",
            UseShellExecute = false,
            CreateNoWindow = true
        });
    }
}
