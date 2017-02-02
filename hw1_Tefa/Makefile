###################################################
# file:		Makefile for homework 1
# 
# This sample Makefile is based on the src + bin folder
# 	organization for your Java programs.  
# 	You can modify it for your own use.
#
# Assume that you have a folder called hw1_Yap 
# that contains:
#
# 	(1) this Makefile
# 	(2) A folder called src.
# 	(3) All java programs are stored directly under src.
#
#  NOTE:  you should replace the folder name hw1_Yap by hwXXX_YYY
#         where  
#              XXX  = number of homework,   and
#              YYY  = your last name.
#
#  Suppose for homework 1, we have these files:
#
# 		Account.java  ATM.java 
# 		HiClass.java  students.txt
#
#      There are two main programs to run found in
#               ATM class
#               HiClass class
#
#      Assume that java programs has this package directive:
#
#               package hw1;
#
#      I.e., all the java files in src belong to a package called "hw1".
#
# Usage:
# 	From any terminal:
#
# 	> cd hw1_Yap		-- go to hw1_Yap folder
#
# 	> make 			-- this will compile all the Java programs in src.
# 				   Note that all the .class files will be put
# 				   in a folder called "bin".  If bin does not
# 				   exist, it will first create bin.
# 	> make ATM		-- this will run the main program in ATM.
# 	> make HiClass		-- this will run the main program in HiClass.
#
# 	> make run args="1 Input.txt Output.txt"
# 				-- this will run the main program 
# 				   with the indicated command line arguments
#	> make zip		-- creates hw1_Yap.zip for submission
#
#
#  -- Professor Chee Yap
##     Data Structures Course (CS 102)
#     Fall 2016
#
###################################################
# Variables:
###################################################

# main programs to run
main1=ATM
main2=HiClass

# name of the package (Note that we need the "/")
package=

# default command line arguments
args1=1111 16 2
args2=0 Hello there

###################################################
# Targets:
###################################################
default: jc

jc javac c compile: 
	test -d bin || mkdir bin
	javac -d bin src/*.java

j java r run: 
	java -classpath . src/${package}${main1} ${args1}

j2 java2 r2 run2: 
	java -classpath . src/${package}${main2} ${args2}

cr: compile run

###################################################
# Housekeeping:
###################################################
clean:
	-rm -f -r bin *~

zip:	clean
	cd ..; zip hw1_Tefa.zip -r hw1_Tefa

###################################################
# End
###################################################

