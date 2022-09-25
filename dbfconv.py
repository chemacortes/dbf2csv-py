#!/usr/bin/env python

import sys
from pathlib import Path

from dbf2csv import DBFile


def usage():
    print(
        """
    dbf2csv - Conversor de DBF a CSV

    Uso:
        dbf2csv dbf_file <csv_file>

    """
    )


if __name__ == "__main__":
    if len(sys.argv) <= 1:
        print("\nERROR: No me has pasado el fichero DBF")
        usage()
        sys.exit(1)

    dbf_file = Path(sys.argv[1])
    csv_file = (
        Path(sys.argv[2])
        if len(sys.argv) > 2
        else dbf_file.with_suffix(".csv")
    )
    print(f"Conversión {dbf_file} --> {csv_file}")

    if not dbf_file.exists():
        print(f"Fichero {dbf_file} no encontrado")
        sys.exit(1)

    db = DBFile(dbf_file.read_bytes())
    print(f"Fichero DBF '{dbf_file}' ({db.desc})")
    print(f"Tamaño: {db.numrec} registros")
    print(f"Última modificación: {db.last_mod}")
    if not db.is_implemented:
        print(f"\n\nFORMATO '{db.desc}' NO IMPLEMENTADO TODAVÍA")
        sys.exit()

    print(f"Creado fichero '{csv_file}'")
    db.to_csv(csv_file)
