#!/bin/bash

echo "Listing contents of the jar:"

for f in *.jar ;


        do
                echo "***********************************************************************************" >> listingClasses.log
                echo "JAR IS = $f" >> listingClasses.log
                jar -tf "$f" >> listingClasses.log ;
        done

echo "Completed."
