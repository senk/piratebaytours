from django.core.management.base import BaseCommand
from api.utils import clear_quotas, calculate_quota


class Command(BaseCommand):
    help = 'Clears and Recalculates Quotas'

    def handle(self, *args, **options):
        clear_quotas()
        calculate_quota()

        self.stdout.write(
            self.style.SUCCESS(
                'Successfully cleared and recalculated quotas'
            )
        )
