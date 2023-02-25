#!/bin/bash

# Check if hgfs directory exists
if [ -d "/mnt/hgfs" ]; then
    echo "hgfs directory already exists."
    exit 0
fi

# Create hgfs directory
sudo mkdir /mnt/hgfs
if [ $? -eq 0 ]; then
    echo "hgfs directory created successfully."
else
    echo "Error creating hgfs directory."
    exit 1
fi

# Add entry to /etc/fstab file
echo "vmhgfs-fuse /mnt/hgfs fuse defaults,allow_other 0 0" | sudo tee -a /etc/fstab > /dev/null

# Reboot system to apply changes
echo "Rebooting system to apply changes."
sudo reboot now
