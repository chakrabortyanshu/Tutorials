import os
rootdir = 'C:\\Users\\as185861\\Downloads\\BANCOL_DEVOPS'

duplicates = {}
for subdir, dirs, files in os.walk(rootdir):
    for file in files:
        if '.java' in file:
            if 'Test' not in file and 'Test' not in subdir: 
                if file in duplicates:
                    duplicates[file].append(subdir)
                else:
                    duplicates[file] = [subdir]

i = 0 
for file in duplicates:
    dirs = duplicates[file]
    if len(dirs) > 1:
        packages = []
        for dir1 in dirs:
            if 'src' in dir1:
                index = dir1.index('src')
                package = dir1[index:len(dir1)]
                if package not in packages:
                    packages += [package]
                    
        if len(packages) == 1:        
            print(file, '->', dirs)
            i = i + 1
        
print(i)
