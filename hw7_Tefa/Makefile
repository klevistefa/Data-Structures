###################################################
# file:		Makefile for homework 7
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
#	...
#
# Usage:
# 	From any terminal:
# 	> cd hw7_Yap		-- go to hw7_Yap folder
# 	> make 			-- to compile all the Java programs in src
# 				   All the .class files will be put
# 				   in a folder called "bin".
#	> make help		-- print help message
# 	> make run1		-- this will run $(main1)
# 	> make run2		-- this will run $(main2)
# 	> ...similarly for runXXX for other XXX values
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
no=7
# my name
student=Tefa


# RECURSIVE MAKE DESIGN:
#
# Suppose you call the target "run2" as follows:
#
#	>>  make run2 n=222
#
#  This will call (say):
#
#	-- make _run2 _mainNo=2 _n=16 _s=111
#
#  (the n=16 default will be overriden by n=222, but the s=111 default remains)
#  That will in turn call
#
#	-- make run _args="$(n) $(s)"
#
#  Which in turn calls
#
#	-- java $(main$(_mainNo)) $(args)
#
#  THUS, there are 3 calls to "make" before we call final target!!!
#
# The command line arguments $(args) is broken up into subvariables
# 	such as nn, ss, mm, etc.
# 	BUT $(args) does NOT directly set nn, ss, mm, etc from these variables.
# 	Instead, it takes its value from $(_args)
#	which is set up in the third make call!
#
# This is only meaningful in the recursive calls of "make":
#	-- the values of _ss, _nn, etc are defaults set up by the initial make
#	-- these are transfered below to ss, nn, etc in the second make
#	-- Why?  because we want to reuse ss, nn, etc in different targets
#	         and each target have their own default values for the
#		 same variables.
ss=$(_ss)
nn=$(_nn)
mm=$(_mm)
pp=$(_pp)

rr=$(_rr)
tt=$(_tt)
uu=$(_uu)
vv=$(_vv)
xx=$(_xx)

aa=$(_aa)
bb=$(_bb)
pat=$(_pat)
args=$(_args) $(_args2)

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
main3z=bst/BSTz
main3y=bst/DisplayBST
main4=freeList/FreeList # FreeList is the String/Zoombini case
main4i=freeList/FreeListi # FreeListi is the Integer case
main5=rev/MyRev
main5z=rev/Rev

main6=sort/Sort
main6z=sort/TestSort
main6t=sort/TestSort
main7=sort/PQueue
main8=graph/GraphTraversal

# HELP:
help h:
	@echo "Help: ==========================="
	@echo "  To run any of the following targets, type"
	@echo "        'make runX' or 'make rX'"
	@echo "        for mainX:"
	@echo "  run1:  " $(main1)
	@echo "  run1z: " $(main1z)
	@echo "  run2:  " $(main2)
	@echo "  run3:  " $(main3)	"  -- for basic BST"
	@echo "  run3z: " $(main3z)	"  -- for Zoombini BST"
	@echo "  run3y: " $(main3y)	"  -- for Display BST"
	@echo "  run4:  " $(main4)	"  -- for freeList with Zoombini names"
	@echo "  run4i: " $(main4i)	"  -- for freeList with integers"
	@echo "  run5:  " $(main5)	"  -- for the 3 reversals"
	@echo "  run6:  " $(main6)	"  -- for sorting (basic)"
	@echo "  run6z: " $(main6z)	"  -- for sorting (timing)"
	@echo "  run6t: " $(main6t) "  -- for testing (sorting)"
	@echo "  run7:  " $(main7)	"  -- for heaps (PQueue)"
	@echo "  run8:  " $(main8)	"  -- for graph traversal"


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
# If you need more stack space, you can set the stack variable with
#	the Java runtime options: e.g., to get 2 MB of stack space, do:
#		>> make runXXX stack="-Xss2m"
j java r run:
	echo "Executing ${main${_mainNo}} with args: ${args}"
	java -classpath bin $(stack) ${main${_mainNo}} ${args} $(ofile)

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
	    _nn=10 _ss=0 \
	    _mm=0 _vv=0 \

_run3:
	make -s run _args="$(nn) $(ss) $(mm) $(vv)"

# run3z: BSTz -- generic BST -- (mm=0 is Integer, mm=1 is String)
r3z run3z runBSTz:
	make -s _run3z _mainNo=3z \
	    _nn=10 _ss=0 \
	    _mm=1 _vv=0 \

_run3z:
	make -s run _args="$(nn) $(ss) $(mm) $(vv)"

# run3y: BSTy -- display BST --
r3y run3y runBSTy:
	make -s _run3y _mainNo=3y \
	    _nn=9 _ss=0 \
	    _mm=1 _vv=0 \

_run3y:
	make -s run _args="$(nn) $(ss) $(mm) $(vv)"


# run4: freeList (T=String)
r4 run4 runFreelist:
	make -s _run4 _mainNo=4 \
	    _nn=12 _ss=0 \

_run4:
	make -s run _args="$(nn) $(ss) $(mm) $(vv)"

# run4i: freeListi (T=Integer)
r4i run4i runFreelisti:
	make -s _run4i _mainNo=4i \
	    _nn=12 _ss=0 \

_run4i:
	make -s run _args="$(nn) $(ss) $(mm) $(vv)"

# run5: reversal (timing experiments)
r5 run5 runRev:
	make -s _run5 _mainNo=5 \
	    _nn=10  _ss=0  _mm=4  _vv=0  _tt=4

_run5:
	make -s run _args="$(nn) $(ss) $(mm) $(vv) $(tt)"

# run5z: reversal
r5z run5z runRevz:
	make -s _run5z _mainNo=5z \
	    _nn=10  _ss=0  _mm=4  _vv=0  _tt=4

_run5z:
	make -s run _args="$(nn) $(ss) $(mm) $(vv) $(tt)"


# run6: basic sorting
r6 run6 sort:
	make -s _run6 _mainNo=6 \
	    _ss=0  _nn=10  _mm=10  _pp=1  _rr=3 \
	    _tt=0  _uu=1  _vv=1  _xx=0

_run6:
	make -s run \
	    _args="$(ss) $(nn) $(mm)  $(pp) $(rr)" \
	    _args2=" $(tt) $(uu) $(vv)  $(xx)"

# run6z: sort experiments
r6z run6z sortExpr:
	make -s _run6z _mainNo=6z \
	    _ss=0  _nn=10  _mm=10  _pp=1  _rr=3 \
	    _tt=0  _uu=1  _vv=1  _xx=1

_run6z:
	make -s run \
	    _args="$(ss) $(nn) $(mm)  $(pp) $(rr)" \
	    _args2=" $(tt) $(uu) $(vv)  $(xx)"

r6t run6t testSort:
	make -s _run6t _mainNo=6t \
			_ss=0  _nn=1000  _mm=50  _pp=10  _rr=3 \
			_tt=0  _uu=1  _vv=1  _xx=1

_run6t:
	make -s run \
	    _args="$(ss) $(nn) $(mm)  $(pp) $(rr)" \
	    _args2=" $(tt) $(uu) $(vv)  $(xx)"

# InsertSort:
r6i r6ins run6ins run6insert:
	make -s run6 tt=0

# BubbleSort:
r6b r6bub run6bub run6bubble:
	make -s run6 tt=1

# SelectSort:
r6s r6sel run6sel run6select:
	make -s run6 tt=2

# MergeSort:
r6m r6mer run6mer run6merge:
	make -s run6 tt=3

# QuickSort:
r6q r6qui run6qui run6quick:
	make -s run6 tt=4

# HeapSort:
r6h r6heap run6heap:
	make -s run6 tt=5

# run7: PQueues via Heaps:
r7 run7 pq pqueue:
	make -s _run7 _mainNo=7 \
	    _ss=0  _nn=10  _mm=10  _pp=1  _rr=3 \
	    _tt=0  _uu=1  _vv=0  _xx=0

_run7:
	make -s run \
	    _args="$(ss) $(nn) $(mm)  $(pp) $(rr)" \
	    _args2=" $(tt) $(uu) $(vv)  $(xx)"


# run8: graph traversal (Careful: ss is NOT seed, but "source")
r8 run8  graph:
	make -s _run8 _mainNo=8 \
	    _pp="src/graph/graph8.txt" \
	    _mm=1 _ss=0

_run8:
	make -s run \
	    _args="$(pp) $(mm) $(ss)"


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
