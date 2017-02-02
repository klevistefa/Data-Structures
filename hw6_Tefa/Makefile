###################################################
# file:		Makefile for homework 6
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
#  For each homework, we may have several main programs to run:
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
no=6
# my name
student=Tefa


# RECURSIVE MAKE DESIGN: suppose you call "make run2 n=222":
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

# this is only meaningful in the recursive calls of "make":
#	-- the values of _ss, _nn, etc are defaults set up by the initial make
#	-- these are transfered below to ss, nn, etc in the second make
#	-- why?  because we want to reuse ss, nn, etc in different targets
#	         but they have their own defaults.
ss=$(_ss)
nn=$(_nn)
mm=$(_mm)
vv=$(_vv)
tt=$(_tt)
pp=$(_pp)
aa=$(_aa)
bb=$(_bb)
pat=$(_pat)
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

# main programs to run
#	NOTE: we do not use the "package" variable, as this
#	is folded into the names "main1" or "main2".
main1=util/MyQueue
main1z=util/MyStack
main2=zoombinis/Zoombinis

main3=bst/BST
# BSTz is the String/Zoombini version
main3z=bst/BSTz
# DisplayBST displays a BST tree
main3y=bst/DisplayBST
# FreeList is the String/Zoombini case
main4=freeList/FreeList
# FreeListi is the Integer case
main4i=freeList/FreeListi
# Rev (with tt=4) gives timing table for 3 versions of Reverse
main5=rev/MyRev

# HELP:
help h:
	@echo "Help: ==========================="
	@echo "  To run any of the following targets, type"
	@echo "        'make runX' or 'make rX'"
	@echo "        for mainX:"
	@echo "  main1: " $(main1)
	@echo "  main1z: " $(main1z)
	@echo "  main2: " $(main2)
	@echo "  main3: " $(main3)
	@echo "  main3z: " $(main3z)
	@echo "  main3y: " $(main3y)
	@echo "  main4: " $(main4)
	@echo "  main4i: " $(main4i)
	@echo "  main5: " $(main5)


# NOTE: the following will compile any java programs in src
#	as well as java programs in subfolders of src:

jc javac c compile:
	test -d bin || mkdir bin
	-javac -Xlint -d bin src/*/*.java
	@if [ "$(defaultPackage)" -eq 1 ]; \
	    then javac -d bin src/*.java; \
	    	echo "Default package present!"; \
	    else \
	    	echo "No default package!"; \
	    fi

# This is ultimately called by each main program:
j java r run:
	echo "Executing ${main${_mainNo}} with args: ${args}"
	java -classpath bin ${main${_mainNo}} ${args} $(ofile)

# Note recursive call to "make" in the following:
#	Each runX (X=1,2,...) target recursively calls subtarget _runX.
#	Each _runX target recursively calls the run target.
# 	REMARK: You can remove the "-s" (silent) flags if you want to debug!

# run1: MyQueue:
r1 run1 runQueue:
	make -s _run1 _mainNo=1 _nn=1 _ss=0

_run1:
	make -s run _args="$(nn) $(ss)"

# run1z: MyStack:
r1z run1z runStack:
	make -s _run1z _mainNo=1z _nn=1 _ss=0

_run1z:
	make -s run _args="$(nn) $(ss)"

# run2: Zoombinis:
r2 run2 runZoombinis:
	make -s _run2 _mainNo=2 _ss=0 _nn=16 _mm=2 _pp="src/zoombinis"

_run2:
	make -s run _args="$(ss) $(nn) $(mm) $(pp)"

# run3: BST
r3 run3 runBST:
	make -s _run3 _mainNo=3 \
	    _nn=10 \
	    _ss=0 \
	    _mm=0 \
	    _vv=0 \

_run3:
	make -s run _args="$(nn) $(ss) $(mm) $(vv)"

# run3z: BSTz -- generic BST -- (mm=0 is Integer, mm=1 is String)
r3z run3z runBSTz:
	make -s _run3z _mainNo=3z \
	    _nn=10 \
	    _ss=0 \
	    _mm=1 \
	    _vv=0 \

_run3z:
	make -s run _args="$(nn) $(ss) $(mm) $(vv)"

# run3y: BSTy -- display BST --
r3y run3y runBSTy:
	make -s _run3y _mainNo=3y \
	    _nn=9 \
	    _ss=0 \
	    _mm=1 \
	    _vv=0 \

_run3y:
	make -s run _args="$(nn) $(ss) $(mm) $(vv)"


# run4: freeList (T=String)
r4 run4 runFreelist:
	make -s _run4 _mainNo=4 \
	    _nn=12 \
	    _ss=0 \

_run4:
	make -s run _args="$(nn) $(ss) $(mm) $(vv)"

# run4i: freeListi (T=Integer)
r4i run4i runFreelisti:
	make -s _run4i _mainNo=4i \
	    _nn=12 \
	    _ss=0 \

_run4i:
	make -s run _args="$(nn) $(ss) $(mm) $(vv)"

# run5: reversal (timing experiments)
r5 run5 runMyRev:
	make -s _run5 _mainNo=5 \
	    _nn=10  _ss=0  _mm=4  _vv=0  _tt=4

_run5:
	make -s run _args="$(nn) $(ss) $(mm) $(vv) $(tt)"


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
	cd ..; \
	    rm -f hw$(no)_$(student).zip; \
	    zip hw$(no)_$(student).zip -r hw$(no)_$(student)


###################################################
# End
###################################################
