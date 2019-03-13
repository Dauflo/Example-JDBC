package com.dauflo.utils;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ParadiseSorter extends TableRowSorter<TableModel> {
    public ParadiseSorter(TableModel tableModel) {
        super(tableModel);
    }
}
