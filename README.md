# Conversión de formato DBF a CSV

Script simple para convertir ficheros de formato DBF a CSV. De momento sólo funciona para ficheros DBF "dBASE level 7".

Uso:

```sh
uv run dbfconv.py <fichero.dbf>
```

También se puede instalar desde <https://pypi.org> como:

```sh
uv tool install dbf2csv-dbase7
```

Una vez descargado se puede ejecutar del siguiente modo:

```sh
dbf2csv <fichero.dbf>
```

Para un uso esporádico, se puede ejecutar sin tener que instalar nada usando uvx:

```sh
uvx --from dbf2csv-dbase7 dbf2csv <fichero.dbf>
```

## Referencias: formatos de ficheros

- [Data File Header Structure for the dBASE Version 7 Table File][1]
- [dBASE File Format (with coding details): DBF and DBT/FPT file structure][2]
- [dBASE Table File Format (DBF)][3]
- [Xbase Data file (*.dbf)][4]

[1]: http://www.dbase.com/KnowledgeBase/int/db7_file_fmt.htm
[2]: http://www.independent-software.com/dbase-dbf-dbt-file-format.html
[3]: https://www.loc.gov/preservation/digital/formats/fdd/fdd000325.shtml
[4]: https://www.clicketyclick.dk/databases/xbase/format/dbf.html
