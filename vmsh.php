<?php

// Change current directory to /mnt
exec('cd /mnt', $output, $return_var);
if ($return_var !== 0) {
  echo 'Error changing directory: ' . implode("\n", $output);
  exit(1);
}

// Create hgfs directory if it doesn't exist
exec('sudo mkdir -p hgfs', $output, $return_var);
if ($return_var !== 0) {
  echo 'Error creating directory: ' . implode("\n", $output);
  exit(1);
}

// Add entry to /etc/fstab file
exec('sudo sh -c \'echo "vmhgfs-fuse /mnt/hgfs fuse defaults,allow_other 0 0" >> /etc/fstab\'', $output, $return_var);
if ($return_var !== 0) {
  echo 'Error adding entry to /etc/fstab: ' . implode("\n", $output);
  exit(1);
}

// Reboot the system to apply changes
exec('sudo reboot now', $output, $return_var);
if ($return_var !== 0) {
  echo 'Error rebooting system: ' . implode("\n", $output);
  exit(1);
}

echo 'Successfully created hgfs folder and added entry to /etc/fstab. System reboot initiated.';

?>
