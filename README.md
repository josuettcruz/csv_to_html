> NetBeans Apache IDE 13

# ProjetoCSV

- Ler e editar arquivo CSV
- Exportá-lo para o formato HTML

# TrackList

## Software de Referência

> TagScanner 6.1.18

[TagScanner](https://www.xdlab.ru/en/index.htm)

[Baixar](https://www.xdlab.ru/en/download.htm)

## Como usar

1. Abra o TagScanner

2. Vá em exportar como

3. Ecolha o formato

> __CSV - Simple__

4. Clique em __editar__

5. Cole o seguinte texto a baixo

```
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
```
