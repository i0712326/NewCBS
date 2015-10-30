package bcel.cardcenter.cbs.marofat.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import bcel.cardcenter.cbs.marofat.entity.MasterCardSettle;

public interface SettlementReader {
	public List<MasterCardSettle> readSettlment(File file) throws IOException ;
}
