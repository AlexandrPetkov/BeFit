package service.impl;

import dao.DAOFactory;
import dao.SourceInit;
import service.SourceService;

public class SourceServiceImpl implements SourceService {

	@Override
	public void sourceInit() {
		DAOFactory factory = DAOFactory.getInstance();
		SourceInit sourceDAO = factory.getSourceInit();

		sourceDAO.init();

	}

	@Override
	public void sourceDestroy() {
		DAOFactory factory = DAOFactory.getInstance();
		SourceInit sourceDAO = factory.getSourceInit();

		sourceDAO.destroy();
	}

}
