const { exec } = require('child_process');

// Change current directory to /mnt
exec('cd /mnt', (err, stdout, stderr) => {
  if (err) {
    console.error(`Error changing directory: ${err}`);
    return;
  }
  
  // Create hgfs directory if it doesn't exist
  exec('sudo mkdir -p hgfs', (err, stdout, stderr) => {
    if (err) {
      console.error(`Error creating directory: ${err}`);
      return;
    }

    // Add entry to /etc/fstab file
    exec('sudo sh -c \'echo "vmhgfs-fuse /mnt/hgfs fuse defaults,allow_other 0 0" >> /etc/fstab\'', (err, stdout, stderr) => {
      if (err) {
        console.error(`Error adding entry to /etc/fstab: ${err}`);
        return;
      }

      // Reboot the system to apply changes
      exec('sudo reboot now', (err, stdout, stderr) => {
        if (err) {
          console.error(`Error rebooting system: ${err}`);
          return;
        }

        console.log('Successfully created hgfs folder and added entry to /etc/fstab. System reboot initiated.');
      });
    });
  });
});
