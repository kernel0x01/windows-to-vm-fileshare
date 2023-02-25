import subprocess

# Change current directory to /mnt
cd_process = subprocess.run(['cd', '/mnt'], capture_output=True)
if cd_process.returncode != 0:
    print(f"Error changing directory: {cd_process.stderr.decode()}")
    exit(1)

# Create hgfs directory if it doesn't exist
mkdir_process = subprocess.run(['sudo', 'mkdir', '-p', 'hgfs'], capture_output=True)
if mkdir_process.returncode != 0:
    print(f"Error creating directory: {mkdir_process.stderr.decode()}")
    exit(1)

# Add entry to /etc/fstab file
echo_command = "echo 'vmhgfs-fuse /mnt/hgfs fuse defaults,allow_other 0 0' | sudo tee -a /etc/fstab"
echo_process = subprocess.run(['/bin/sh', '-c', echo_command], capture_output=True)
if echo_process.returncode != 0:
    print(f"Error adding entry to /etc/fstab: {echo_process.stderr.decode()}")
    exit(1)

# Reboot the system to apply changes
reboot_process = subprocess.run(['sudo', 'reboot', 'now'], capture_output=True)
if reboot_process.returncode != 0:
    print(f"Error rebooting system: {reboot_process.stderr.decode()}")
    exit(1)

print("Successfully created hgfs folder and added entry to /etc/fstab. System reboot initiated.")
