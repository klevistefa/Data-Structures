###################################################
# file:		Makefile for homework 4
#
#	WARNING: for successive homeworks, our Makefile may
#		evolve to make it slightly more general!
#
# This sample Makefile is based on the src + bin folder
# 	organization for your Java programs.  
# 	You can modify it for your own use.
#
# Assume that you have a folder called hw4_Yap containing
# 	(1) this Makefile
# 	(2) A folder called src.
# 	(3) All java programs are stored directly under src.
#
#  NOTE:  you should replace the folder name hw1_Yap by hwXXX-YYY
#         where   XXX  = number of homework,   and
#                 YYY  = your last name.
#
#  For each homework, we may have several main programs to run: e.g.,
#	$(main1) = MyArrayStringLog
#	$(main2) = Zoombinis/Zoombinis
#
# Usage:
# 	From any terminal:
# 	> cd hw4_Yap		-- go to hw4_Yap folder
# 	> make 			-- to compile all the Java programs in src
# 				   All the .class files will be put
# 				   in a folder called "bin".  
# 	> make run1		-- this will run $(main1) 
# 	> make run2		-- this will run $(main2)
#
#	> make clean		-- removes any compiled programs
#	> make zip		-- creates zip file hw1_Yap.zip for submission
#				   (zip file is in the parent folder "..")
#				   CAREFUL: make sure that $(no) and $(student)
#				   in Makefile is correctly setup first!
#
#  -- Professor Chee Yap
#     Data Structures Course (CS102, Section 7)
#     Fall 2016
#
###################################################
# Variables:
###################################################
# number of homework
no=4
# my name
student=Tefa

# main programs to run
#	NOTE: we do not use the "package" variable, as this
#	is folded into the names "main1" or "main2".
main1=util/MyQueue
main2=zoombinis/Zoombinis
main3=match/Match
main4=expr/Expr

# RECURSIVE MAKE DESIGN: suppose you call "make run2 n=222":
#
#	make run2 n=222
#  calls
#	make _run2 _mainNo=2 _n=16 _s=111
#  calls
#	make run _args="$(n) $(s)"
#  calls target
#	java $(main$(_mainNo)) $(args)
#
#  THUS, there are 3 calls to make before we call final target!!!
#
# The command line arguments $(args) is broken up into subvariables
# 	but it is NOT directly set from these!
# 	Instead, it takes its value from $(_args)
#	which is set in the third make call!

# this is only meaningful in the recursive call to make:
#	-- the values of _s and _n are defaults set up by the second make
s=$(_s)
n=$(_n)
m=$(_m)
v=$(_v)
pp=$(_pp)
args=$(_args)

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
	    	echo "Default package present!"; \
	    else \
	    	echo "No default package!"; \
	    fi

# This is called for each main program:
j java r run:
	echo "Executing ${main${_mainNo}} with args: ${args}"
	java -classpath bin ${main${_mainNo}} ${args} $(ofile)

# Note recursive call to "make" in the following:
#	Each runX (X=1,2,...) target recursively calls a subtarget called _runX.
#	Each _runX target recursively calls the generic run target.
# 	REMARK: Please remove the "-s" (silent) flags if you want to debug!

# MyQueue:
#####################
r1 run1:
	make -s _run1 _mainNo=1 _n=1 _s=0

_run1:
	make -s run _args="$(n) $(s)"

# Zoombinis:
#####################
r2 run2:
	make -s _run2 _mainNo=2 _s=0 _n=16 _m=2 _pp="src/zoombinis"

_run2:
	make -s run _args="$(s) $(n) $(m) $(pp)"

# ADAPT for your use:
#####################
r3 run3: 
	make -s _run3 _mainNo=3 _nn=1 _pat="src/match/pfile.txt" _aa="src/match/afile.txt" _bb="src/match/bfile.txt"
 
_run3: 
	make -s run _args="$(nn) $(pat) $(aa) $(bb)"


# ADAPT for your use:
#####################
r4 run4:
	make -s _run4 _mainNo=4 _n=0 _s=0 _v=0

_run4:
	make -s run _args="$(n) $(s) $(v)"


cr: compile run


###################################################
# Various Tests:
###################################################
alltests at: t1 t2 t3 

allshow as:
	gvim output-test1 \
		output-test2 \
		output-test3 \

test1 t1:
	make run1 ofile="2&> output-test1"

test2 t2:
	make run1 args1="10000" ofile="2&> output-test2"

test3 t3 simple:
	make run2 ofile="2&> output-test3"



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
zip:	vclean
	cd ..; zip hw$(no)_$(student).zip -r hw$(no)_$(student)


###################################################
# End
###################################################

