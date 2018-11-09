import logging
import sys

from whynot.update import update_all
from whynot.storage import storage
from whynot.settings import settings


logging.basicConfig(stream=sys.stdout, level=logging.DEBUG)


storage.db_uri = settings['MONGODB_URI']
storage.db_name = settings['MONGODB_DB_NAME']


if len(sys.argv) > 1:
    update_all(sys.argv[1:])
else:
    update_all()
