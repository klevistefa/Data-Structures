###################################################
# file:		Makefile for homework 3
#
# This sample Makefile is based on the src + bin folder
# 	organization for your Java programs.  
# 	You can modify it for your own use.
#
# Assume that you have a folder called hw1_Yap containing
# 	(1) this Makefile
# 	(2) A folder called src.
# 	(3) All java programs are stored directly under src.
#
#  NOTE:  you should replace the folder name hw1_Yap by hwXXX-YYY
#         where   XXX  = number of homework,   and
#                 YYY  = your last name.
#
#  For homework 3, we have two main programs to run:
#	$(main1) = MyArrayStringLog
#	$(main2) = Zoombinis/Zoombinis
#
# Usage:
# 	From any terminal:
# 	> cd hw3_Yap		-- go to hw3_Yap folder
# 	> make 			-- to compile all the Java programs in src
# 				   All the .class files will be put
# 				   in a folder called "bin".  
# 	> make run1		-- this will run $(main1) 
# 	> make run2		-- this will run $(main2)
#
#	> make clean		-- removes any compiled programs
#	> make zip		-- creates zip file hw1_Yap.zip for submission
#				   (zip file is in the parent folder "..")
#
#  -- Professor Chee Yap
#     Data Structures Course (CS102, Section 7)
#     Fall 2016
#
###################################################
# Variables:
###################################################
# number of homework
no=3
# my name
student=Tefa

# main programs to run
#	NOTE: we no longer have the "package" variable, as this
#	is folded into the names "main1" or "main2".
main1=stringLog/MyArrayStringLog
main2=zoombinis/NewZoombinis
main3=stringLog/NewArrayStringLog
main4=linkedList/Node2

# The command line arguments is broken up into several components:
# 	WHY do we do this?  

# Command line arguments for main1:
# Random seed
s = 0
# length of array
n = 200
# number of entries in array to print
m = 3
#
args1= ${n} ${m} $(s)


# Command line arguments for main2:
# Random seed
ss = 0
# Number of Zoombini names
nn = 16
# Mode of Zoombini names
mode = 0
# Path to Zoombini syntax files
pp = "src"
#
args2= ${ss} ${nn} ${mode} ${pp}

# Command line arguments for main3:
# Random seed
s2 = 0
# Number of Zoombini names
n2 = 600
# Number of names shown (bottom/top)
m2 = 5
# Mode of names
mode2 = 1
# Path to Zoombini syntax files
pp = "src"
#
args3= ${s2} ${n2} ${m2} ${mode2} ${pp}

# Command line arguments for main4:
# Random seed
s3 = 0
# Number of Zoombini names
n3 = 16
# Mode of names
mode3 = 5
# Path to Zoombini syntax files
pp = "src"
#
args4= ${s3} ${n3} ${mode3} ${pp}


###################################################
# Targets:
###################################################
default: jc

# Makefile will first detect if there is a default package in src:
#	if there is one, set defaultPackage=1.
ifneq ("$(wildcard src/*.java)","")
    defaultPackage = 1
else
    defaultPackage = 0
endif

# NOTE: the following will compile any java programs in src
#	as well as java programs in subfolders of src:

jc javac c compile: 
	test -d bin || mkdir bin
	-javac -d bin src/*/*.java
	@if [ "$(defaultPackage)" -eq 1 ]; \
	    then javac -d bin src/*.java; \
	    fi

j java r run r1 run1: 
	java -classpath bin ${main1} ${args1} $(ofile)

j2 java2 r2 run2: 
	java -classpath bin ${main2} ${args2} $(ofile)
	
j3 java3 r3 run3: 
	java -classpath bin ${main3} ${args3} $(ofile)
	
j4 java4 r4 run4: 
	java -classpath bin ${main2} ${args4} $(ofile)
	
j5 java5 r5 run5:
	java -classpath bin ${main4}


cr: compile run


###################################################
# Various Tests:
###################################################
alltests at: t1 t2 t3 t4 t5 t6

allshow as:
	gvim output-test1 \
		output-test2 \
		output-test3 \
		output-test4 \
		output-test5 \
		output-test6 \

test1 t1:
	make run1 ofile="2&> output-test1"

test2 t2:
	make run1 args1="10000" ofile="2&> output-test2"

test3 t3 simple:
	make run2 ofile="2&> output-test3"

test4 t4 american:
	make run2 mm=1 ofile="2&> output-test4"

test5 t5 chinese:
	make run2 mm=2 ofile="2&> output-test5"

test6 t6 korean:
	make run2 mm=7 ofile="2&> output-test6"


###################################################
# Housekeeping:
###################################################
clean:
	-rm -f bin/${main1}*.class \
		bin/${main2}*.class \
	    .*~ *~ \
	    src/*~ src/.*~ \
	    src/*/*~ src/*/.*~

veryclean vclean: clean
	-rm -r bin

# Create zip file for submission.
#	The zip file will be found in the parent folder:
zip:	clean
	cd ..; zip hw$(no)_$(student).zip -r hw$(no)_$(student)


###################################################
# End
###################################################

