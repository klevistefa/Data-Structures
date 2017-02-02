###################################################
# file:		Makefile for homework 2
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
#  NOTE:  you should replace the folder name hw2_Yap by hwXXX_YYY
#         where  
#              XXX  = number of homework,   and
#              YYY  = your last name in full.
#
#  Suppose for homework 2, we have these files:
#
# 		Cash.java, Account.java, Client.java, ...
#
#      The main program to run is an ATM machine found in Cash.java.
#
#      There are two possibilities:
#	(P1) all these Java files has a package directive as the first line:
#
#               package hw2;
#
#      I.e., all the java files in src belong to a package called "hw1".
#
#	(P2) none of the Java files has package directives (i.e.,
#		the "Default package".
#
#      NOTE: for hw1, we allow you both possibilities.
#		This Makefile can handle both cases. 
#
# Usage:
# 	From any terminal:
#
# 	> cd .../hw2_Yap -- go to hw2_Yap folder
#
# 	> make 		-- this will compile all the Java programs in src.
# 			   Note that all the .class files will be put
# 			   in a folder called "bin".  If bin does not
# 			   exist, it will first create bin.
# 	> make run	-- this will run the Cash ATM.
# 	> make run args=2
# 			-- this will run the Cash ATM
# 			   with the indicated command line arguments
#	> make clean	-- removes any compiled programs
#	> make zip	-- creates the zip file hw2_Yap.zip for submission
#			   (the zip file will be found in the parent folder)
#
#
#  -- Professor Chee Yap
#     Data Structures Course (CS 102)
#     Fall 2016
#
###################################################
# Variables:
###################################################
# Homework Number and Student Last Name:
no=2
name=Tefa

# main program to run
main1=Cash
main2=HiClass

# name of the package 
package=hw$(no)/	# Use this if the package is "hw2" (NOTE: "/" is needed)
package=		# Use this if using "Default package" (no package)

# default command line arguments
ifile=< src/Input.txt		# Use this for file input
ifile=				# Use this for interactive input

args1=Input.txt
args1=
args2=0 "ANYTHING YOU WANT"

###################################################
# Targets:
###################################################
default: jc

jc javac c compile: 
	test -d bin || mkdir bin	# create bin folder if necessary
	javac -d bin src/$(package)*.java

Cash: run 		# this is only for hw2

j java r run r1 run1: 
	java -classpath bin ${package}${main1} ${args1} $(ifile)

j2 java2 r2 run2: 
	java -classpath bin ${package}${main2} ${args2}

cr: compile run

###################################################
# Housekeeping:
###################################################
clean:
	-rm -f bin/${package}*.class *~

# Create zip file for submission.
#	The zip file will be found in the parent folder:
zip:	clean
	cd ..; zip hw$(no)_$(name).zip -r hw$(no)_$(name)
help:   
	echo $(HOME)
	echo $(PATH)
###################################################
# End
###################################################

