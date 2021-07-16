import os
sourcedir = 'C:\\repositories\\git\\bcol\\development\\cla-bcol-authentic\\bcol\\bcol-product\\src\\main\\java'
destdir = 'C:\\repositories\\git\\bcol\\development\\cla-bcol-authentic\\bcol\\bcol-extension\\src\\main\\java'


def listoffiles (path):
    listoffiles = {}
    for subdir, dirs, files in os.walk(path):
        for file in files:
            if '.java' in file:
                if 'Test' not in file and 'Test' not in subdir: 
                    if file in listoffiles:
                        listoffiles[file].append(subdir)
                    else:
                        listoffiles[file] = [subdir]

    return listoffiles

sourcefiles = listoffiles(sourcedir)
destfiles = listoffiles(destdir)
i = 0 
for file in sourcefiles:
    if file in destfiles:
        dirs = sourcefiles[file]
        packages = []
        for dir1 in dirs:
            if 'src' in dir1:
                index = dir1.index('src')
                package = dir1[index:len(dir1)]
                destdirs = destfiles[file]
                for destpackage in destfiles[file]:
                    if package in destpackage:
                        packages += [destpackage]
                    
        print(file,' --> source : ', sourcefiles[file], ' --> dest: ', packages)
