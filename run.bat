IF "%1"=="" (
	SET INPUT_FILE=etc\Boys_Enrollment.csv
) ELSE (
	SET INPUT_FILE=%1
)
java -cp "bin/;." iteration1.Main %INPUT_FILE%

