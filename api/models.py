from django.db import models


class Ship(models.Model):
    name = models.CharField(max_length=30)
    seats = models.IntegerField()

    def __str__(self):
        return self.name


class Tour(models.Model):
    name = models.CharField(max_length=30)
    date = models.DateField()
    time = models.TimeField()
    ship = models.ForeignKey(Ship)

    def __str__(self):
        return self.name


class Agent(models.Model):
    name = models.CharField(max_length=30)

    def __str__(self):
        return self.name


class Quota(models.Model):
    agent = models.ForeignKey(Agent)
    count = models.IntegerField()
    tour = models.ForeignKey(Tour)


class Customer(models.Model):
    name = models.CharField(max_length=30)

    def __str__(self):
        return self.name


class Reservation(models.Model):
    count = models.IntegerField()
    tour = models.ForeignKey(Tour)
    customer = models.ForeignKey(Customer)
