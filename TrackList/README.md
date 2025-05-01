> NetBeans Apache IDE 13

## Como usar

1. Exportar
2. csv simple

# TagScanner

[TagScanner 6.1.18](https://www.xdlab.ru/en/index.htm)

[TagScanner - Download](https://www.xdlab.ru/en/download.htm)

# Tagscanner export script

$file_name TrackList.csv
$file_notes Raw comma-separated text
$file_encoding utf-8
$file_writebom 1

$document_open
$select %_index%,0
%title%;%artist%;%album%;%track%;%year%;%genre%;%_length_sec%;%_filesize%;%_filedate%;%filepath%;%filenameext%
$endselect
$document_close
